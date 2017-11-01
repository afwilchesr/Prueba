import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Prueba {

	static class Pair implements Comparable<Pair> {
		long start;
		long end;

		public Pair(long start, long end) {
			super();
			this.start = start;
			this.end = end;
		}

		public int compareTo(Pair other) {
			return Long.compare(start, other.start);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] split = reader.readLine().split(" ");
		long n = Long.parseLong(split[0]);
		long m = Long.parseLong(split[1]);
		long trains = Long.parseLong(split[2]);

		HashMap<Long, List<Pair>> map = new HashMap<>();

		for (long i = 0; i < trains; i++) {
			split = reader.readLine().split(" ");
			long r = Long.parseLong(split[0]);
			long c1 = Long.parseLong(split[1]);
			long c2 = Long.parseLong(split[2]);
			Pair p = new Pair(c1, c2);
			if (!map.containsKey(r))
				map.put(r, new ArrayList<Pair>());
			map.get(r).add(p);
		}
		long ans = 0;
		for (long i = (map.keySet().size() + 1); i <= n; i++)
			ans += m;
		
		for (Long row : map.keySet()) {
			Collections.sort(map.get(row));
			long max = 1L;
			for (int i = 0; i < map.get(row).size(); i++) {
				if (max < map.get(row).get(i).start)
					ans += map.get(row).get(i).start - max;
				if (max <= map.get(row).get(i).end)
					max = map.get(row).get(i).end + 1L;
			}
			ans += m - max + 1;
		}

		System.out.println(ans);
	}

}
