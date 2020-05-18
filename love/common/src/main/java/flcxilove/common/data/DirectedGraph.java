package flcxilove.common.data;

import flcxilove.common.exception.SystemException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 有向图数据结构
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-16 10:55
 */
public class DirectedGraph<T> implements Iterable<T>, Serializable {

  private static final long serialVersionUID = 2414258404102262150L;
  /**
   * 有向图通过邻接表方法进行存储
   */
  private final Map<T, Set<T>> graph;

  /**
   * 构造函数
   */
  public DirectedGraph() {
    this.graph = new HashMap<>();
  }

  /**
   * 构造函数
   *
   * @param graph 有向图
   */
  public DirectedGraph(Map<T, Set<T>> graph) {
    this.graph = graph;
  }

  /**
   * 新增顶点到图中
   *
   * @param node 新增顶点数据
   *
   * @return 新增操作是否成功
   */
  public Boolean addNode(T node) {

    if (this.graph.containsKey(node)) {
      return Boolean.FALSE;
    }

    // 新增顶点空输出边集合
    this.graph.put(node, new HashSet<>());

    return Boolean.TRUE;
  }

  /**
   * 新增两顶点之间的边
   *
   * @param start 起始顶点数据
   * @param dest 结束顶点数据
   */
  public void addEdge(T start, T dest) {

    if (!this.graph.containsKey(start)) {
      throw new SystemException("");
    } else if (!this.graph.containsKey(dest)) {
      throw new SystemException("");
    }

    // 新增有向边
    this.graph.get(start).add(dest);
  }

  /**
   * 去除两顶点之间的边
   *
   * @param start 起始顶点数据
   * @param dest 结束顶点数据
   */
  public void removeEdge(T start, T dest) {

    if (!this.graph.containsKey(start)) {
      throw new SystemException("");
    } else if (!this.graph.containsKey(dest)) {
      throw new SystemException("");
    }

    // 去除有向边
    this.graph.get(start).remove(dest);
  }

  /**
   * 判定两顶点间是否存在有向边
   *
   * @param start 起始顶点数据
   * @param dest 结束顶点数据
   *
   * @return 判定结果
   */
  public Boolean edgeExists(T start, T dest) {

    if (!this.graph.containsKey(start)) {
      throw new SystemException("");
    } else if (!this.graph.containsKey(dest)) {
      throw new SystemException("");
    }

    return this.graph.get(start).contains(dest);
  }

  /**
   * 获取顶点出发路径集合
   *
   * @param node 顶点数据
   *
   * @return 顶点出发路径集合
   */
  public Set<T> edgesFrom(T node) {

    Set<T> arcs = this.graph.get(node);
    if (arcs == null) {
      throw new SystemException("");
    }

    return Collections.unmodifiableSet(arcs);
  }

  /**
   * 获取有向图信息
   *
   * @return 有向图信息
   */
  public Map<T, Set<T>> getGraph() {
    return this.graph;
  }

  @Override
  public Iterator<T> iterator() {
    return this.graph.keySet().iterator();
  }

  /**
   * 获取图中顶点数量
   *
   * @return 图中顶点数量
   */
  public int size() {
    return this.graph.size();
  }

  /**
   * 判定图是否为空
   *
   * @return 判定结果
   */
  public Boolean isEmpty() {
    return this.graph.isEmpty();
  }
}
