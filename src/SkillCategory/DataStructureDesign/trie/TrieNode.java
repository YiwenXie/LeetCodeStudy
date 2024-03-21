package SkillCategory.DataStructureDesign.trie;

/**
 * @author ywxie
 * @date 2022/6/20 17:37
 * @describe TrieNode 节点本身只存储 val 字段，并没有一个字段来存储字符，
 * 字符是通过子节点在父节点的 children 数组中的索引确定的。
 * Trie 树用「树枝」存储字符串（键），用「节点」存储字符串（键）对应的数据（值）。
 */
public class TrieNode<V> {
    /**
     * 这个 val 字段存储键对应的值，children 数组存储指向子节点的指针。一个节点有 256 个子节点指针
     * 但是和之前的普通多叉树节点不同，TrieNode 中 children 数组的索引是有意义的，代表键中的一个字符。
     * 比如说 children[97] 如果非空，说明这里存储了一个字符 'a'，因为 'a' 的 ASCII 码为 97。
     * 我们的模板只考虑处理 ASCII 字符，所以 children 数组的大小设置为 256。
     **/
    V val = null;
    TrieNode<V>[] children = new TrieNode[256];

}
