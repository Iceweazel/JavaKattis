import java.util.LinkedList;
import java.util.Iterator;

public class Internet {

    Kattio io;

    int houses;
    int connected;

    LinkedList<Integer> neighbours[];

    void dfs(int u, boolean[] visited) {

        visited[u] = true;

        Iterator<Integer> it = neighbours[u].iterator();
        while(it.hasNext()) {
            int v = it.next();
            if(!visited[v]) {
                dfs(v, visited);
            }
        }


    }

    /*
    House number 1 is connected to main internet network, and want to connect
    the rest of village by connect them in pairs. Use a dfs from house number 1
    to find the largest connected graph from there.
    */
    public Internet() {
        io = new Kattio(System.in, System.out);

        houses = io.getInt();
        connected = io.getInt();

        neighbours = new LinkedList[houses + 1];

        for(int i = 1; i <= houses; i++) {
            neighbours[i] = new LinkedList<Integer>();
        }
        int u, v;
        while(io.hasMoreTokens()) {
            u = io.getInt();
            v = io.getInt();

            neighbours[u].add(v);
            neighbours[v].add(u);
        }

        boolean[] visited = new boolean[houses + 1];

        dfs(1, visited);

        int stillUnconnected = 0;

        for(int i = 1; i <= houses; i++) {

            if(!visited[i]) {
                io.println(i);
                stillUnconnected++;
            }
        }
        if(stillUnconnected == 0) {
            io.println("Connected");
        }
        io.close();

    }

    public static void main(String[] args) {
        new Internet();
    }
}
