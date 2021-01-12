export default{
  buildTree(data, obj) {
    let tree = [];
    tree.push({
      id: 1,
      label: obj.id + "[" + obj.name + "]",
      children: [],
    });
    for (let i = 0; i < data.length; i++) {
      tree[0].children.push({
        id: data[i].id,
        label:
          data[i].id +
          "[" +
          data[i].name +
          "]" +
          "×" +
          data[i].num +
          data[i].unit,
      });
    }
    return tree;
  },
}