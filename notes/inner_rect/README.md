## 두 사각형이 직교좌표계에 있을때 두 사각형을 연결하는 사각형 구하기

```java
private int makeEdge(Node nodeA, Node nodeB) {
    /*
     *
     *   (0) 여기서 노드는 섬(직사각형) 이다.
     *   (1) 두 노드 관계에서 가로가 겹치면서, 세로가 겹치는 일은 있을 수 없다.
     *   (2) 두 노드가 겹치는 관계일때 내부 사각형을 Node(ix0, iy0, ix1, iy0)으로 정의한다.
     *
     *                  ix0
     *         +--------+
     *         |        |
     *         |        |
     *         +--------+............*  iy0
     *                  :            :
     *                  :            :
     *                  :            :
     *             iy1  *.............+--------+
     *                                |        |
     *                                |        |
     *                                +--------+
     *                              ix1
     */

    int ix0 = Math.max(Math.min(nodeA.x1, nodeB.x0), Math.min(nodeA.x0, nodeB.x1));
    int ix1 = Math.min(Math.max(nodeA.x1, nodeB.x0), Math.max(nodeA.x0, nodeB.x1));
    int iy0 = Math.max(Math.min(nodeA.y1, nodeB.y0), Math.min(nodeA.y0, nodeB.y1));
    int iy1 = Math.min(Math.max(nodeA.y1, nodeB.y0), Math.max(nodeA.y0, nodeB.y1));

    int w = ix1 - ix0 - 1;
    int h = iy1 - iy0 - 1;


    if (w > 0 && !isCollisionHorizontal(ix0 + 1, iy0, ix1 - 1, iy1)) {
      // 가로로 연결된 다리
      return w;
    }

    if (h > 0 && !isCollisionVertical(ix0, iy0 + 1, ix1, iy1 - 1)) {
      // 세로로 연결된 다리
      return h;
    }

    // 두 섬 사이에는 다리를 배치할 수 없음
    return -1;
  }

  private boolean isCollisionHorizontal(int x0, int y0, int x1, int y1) {
    for (Node node : nodeList) {
      if (x0 <= node.x0 && node.x1 <= x1 &&
          y0 >= node.y0 && node.y1 >= y1
      ) {
        return true;
      }
    }
    return false;
  }

  private boolean isCollisionVertical(int x0, int y0, int x1, int y1) {
    for (Node node : nodeList) {
      if (x0 >= node.x0 && node.x1 >= x1 &&
          y0 <= node.y0 && node.y1 <= y1
      ) {
        return true;
      }
    }
    return false;
  }

  // ----------------------------------------------------------

  static class Node {
    int x0, y0;
    int x1, y1;

    public Node(int x0, int y0, int x1, int y1) {
      this.x0 = x0; this.y0 = y0;
      this.x1 = x1; this.y1 = y1;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d, %d, %d]", x0, y0, x1, y1);
    }
  }
```
