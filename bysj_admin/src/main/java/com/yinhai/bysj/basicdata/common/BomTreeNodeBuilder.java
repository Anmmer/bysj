package com.yinhai.bysj.basicdata.common;

import com.yinhai.bysj.basicdata.vo.WlInfoVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BomTreeNodeBuilder {
    public static Map<String,BomTreeNode> build(List<WlInfoVo> treeNodes){
        Map<String,BomTreeNode> nodes=new HashMap<>();
        Map<String,Integer> mapC = new HashMap<>();
        String pid;
        int indexC = 0;
        for (int i = 0;i<treeNodes.size();i++) {
            if (nodes.get(treeNodes.get(i).getPid()) != null) {
                continue;
            } else {
                pid = treeNodes.get(i).getPid();
                BomTreeNode bom = new BomTreeNode();
                bom.setId(treeNodes.get(i).getPid());
                bom.setName(treeNodes.get(i).getName());
                nodes.put(bom.getId(), bom);
            }

            for (int j = 0; j < treeNodes.size(); j++) {
                if (treeNodes.get(j).getPid().equals(pid)) {
                    mapC.put(treeNodes.get(j).getId(), indexC);
                    BomTreeNode bom = new BomTreeNode();
                    bom.setId(treeNodes.get(j).getId());
                    bom.setName(treeNodes.get(j).getName());
                    bom.setNum(treeNodes.get(j).getNum());
                    bom.setUnit(treeNodes.get(j).getUnit());
                    bom.setLabel(bom.getId() + "[" + treeNodes.get(j).getName() + "]" + "×" + treeNodes.get(j).getNum() + treeNodes.get(j).getUnit());
                    nodes.get(pid).getChildren().add(bom);
                    indexC++;
                }
            }
            for (int j = 0; j < treeNodes.size(); j++) {
                if (mapC.get(treeNodes.get(j).getPid()) != null) {
                    BomTreeNode bom = new BomTreeNode();
                    bom.setId(treeNodes.get(j).getId());
                    bom.setName(treeNodes.get(j).getName());
                    bom.setNum(treeNodes.get(j).getNum());
                    bom.setUnit(treeNodes.get(j).getUnit());
                    bom.setLabel(bom.getId() + "[" + treeNodes.get(j).getName() + "]" + "×" + treeNodes.get(j).getNum() + treeNodes.get(j).getUnit());
                    nodes.get(pid).getChildren().get(mapC.get(treeNodes.get(j).getPid())).getChildren().add(bom);
                }
            }
            indexC = 0;
            mapC.clear();
        }
        return nodes;
    }
}
