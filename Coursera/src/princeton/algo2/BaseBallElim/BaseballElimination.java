package princeton.algo2.BaseBallElim;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Immutable Class
public class BaseballElimination {
    private final String[] teams;
    private final int[][] teamData; //number of wins, number of losses, numbers of games left
    private final int[][] gamesLeft; //games by team 
    private Map<String,Integer> teamIndex;
    private int maxWins = Integer.MIN_VALUE;
    private int maxWinTeamIndex;
    private int totalGamesRem = 0;
    //private Map<String,String[]> subSet;
    private List<String>[] l;
    public BaseballElimination(String filename) {                   // create a baseball division from given filename in format specified below
        In in = new In(filename);
        int numTeams = in.readInt();
        //init
        teamIndex = new HashMap<String,Integer>();
        teams = new String[numTeams];
        teamData = new int[numTeams][4];
        gamesLeft = new int[numTeams][numTeams];
        l = new List[numTeams];
        //read
        in.readLine();
        for (int i = 0; i < numTeams; i++) {
            String[] teamDataArr = in.readLine().trim().split("\\s+");
            teams[i] = teamDataArr[0].trim();
            teamIndex.put(teams[i], i);
            int wins = Integer.parseInt(teamDataArr[1]);
            //optimization
            if (wins > maxWins) {
                maxWins = wins;
                maxWinTeamIndex = i;
            }
            teamData[i][0] = wins;
            teamData[i][1] = Integer.parseInt(teamDataArr[2]);
            teamData[i][2] = Integer.parseInt(teamDataArr[3]);
            teamData[i][3] = 0;  //actual number of games left
            for (int k = 0; k < numTeams; k++) {
                int gameAgainstK = Integer.parseInt(teamDataArr[4+k]);
                teamData[i][3] += gameAgainstK;
                gamesLeft[i][k] = gameAgainstK;
            }
            totalGamesRem += teamData[i][3];
        }
        //each game will get counted twice
        assert (totalGamesRem%2 != 0);
        totalGamesRem = totalGamesRem/2;
    }
    public  int numberOfTeams() {                       // number of teams
        return teams.length;
    }
    public Iterable<String> teams() {                                // all teams
        return Arrays.asList(teams);
    }
    public int wins(String team) {                      // number of wins for given team
        return teamData[getIndex(team)][0];
    }
    public int losses(String team) {                   // number of losses for given team
        return teamData[getIndex(team)][1];
    }
    public int remaining(String team) {                // number of remaining games for given team
        return teamData[getIndex(team)][2];
    }
    public int against(String team1, String team2) {   // number of remaining games between team1 and team2
        return gamesLeft[getIndex(team1)][getIndex(team2)];
    }
    public boolean isEliminated(String team) {             // is given team eliminated?
        int index = getIndex(team);
        //trivial elimination
        int maxWinsForIndexP = teamData[index][0] + teamData[index][2];
        if (maxWinsForIndexP < maxWins) {
            l[index] = (List<String>)Arrays.asList(teams[maxWinTeamIndex]);
            return true;
        }
        //Non-trivial elimination via flow
        int n = this.numberOfTeams();
        int gameVertices = 0;
        for (int i=1; i <= n-2; i++)
            gameVertices += i;
        FlowNetwork G = new FlowNetwork(n + gameVertices + 2); //1 source vertex and 1 sink vertex , team[index] vertex will be disjoint
        int t = n + gameVertices + 1;
        int s = t-1;
        //add an edge from s to each game vertex with capacity equals to games remaining
        int gameVertex = n;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                for (int j = i+1; j < n; j++) {
                    if (j != index) {
                        int gamesLeftIJ = gamesLeft[i][j];
                        //if (gamesLeftIJ != 0 ) {
                            G.addEdge(new FlowEdge(s, gameVertex, gamesLeftIJ));
                        //}
                        //connect this game vertex to the two teams i and j 
                        G.addEdge(new FlowEdge(gameVertex, i, Double.POSITIVE_INFINITY));
                        G.addEdge(new FlowEdge(gameVertex, j, Double.POSITIVE_INFINITY));
                        gameVertex++;
                    } //if end
                } //inner for loop ends
            } //if end
        }
        //Add edge from each team vertex to sink t
        for (int i = 0; i < n; i++) {
            if (i != index) {
                G.addEdge(new FlowEdge(i, t, maxWinsForIndexP-teamData[i][0]));
            }
        }
        //print the flow network graph for testing
        //System.out.println(G.toString());
        //--------------------------*Flow network constructed*---------------------------
        FordFulkerson ff = new FordFulkerson(G, s, t);
        //
        //System.out.println("For team :"+teams[index]);
        //calculate total games rem without atlanta
        int gamesRemWithoutIndexTeam = totalGamesRem;
        for (int i = 0; i < teams.length; i++) {
            gamesRemWithoutIndexTeam -= gamesLeft[index][i]; //if i == index , gamesLeft = 0
        }
        if (ff.value() != gamesRemWithoutIndexTeam) {
            l[index] = new ArrayList<String>();
            for (int i = 0; i < teams.length; i++) {
                if (i != index && ff.inCut(i)) {
                    l[index].add(teams[i]);
                }
            }
            return true;
        }
        else
            return false;
    }
    public Iterable<String> certificateOfElimination(String team) { // subset R of teams that eliminates given team; null if not eliminated
        int index = getIndex(team);
        List<String> sub = l[index];
        if (sub == null) {
            isEliminated(team);
        }
        sub = l[index];
        if (sub != null && sub.isEmpty()) {
            return null;
        }
        return sub;
    }
    private int getIndex(String team) {
        Integer index = teamIndex.get(team);
        if (index == null)
            throw new IllegalArgumentException();
        return index;
    }
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("teams4.txt");
        for (String team : division.teams()) {
            System.out.println("team : " + team);
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
