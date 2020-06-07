import java.util.ArrayList;
import java.util.List;

class Pair
{
	int first, second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

class SnakeSequenceCopied
{
	// Construct maximum length snake sequence from given tail
	// and L[] matrix
	public static  List<Pair> constructPath(int L[][], int[][] grid,
														Pair tail)
	{
		List<Pair> path = new ArrayList<>();
		path.add(tail);

		int i = tail.first;
		int j = tail.second;

		// start from snake's tail till snake's head
		while (L[i][j] != 0)
		{
			if (i - 1 >= 0 && L[i][j] - L[i - 1][j] == 1
					&& Math.abs(grid[i - 1][j] - grid[i][j]) == 1)
			{
				path.add(new Pair(i - 1, j));
				i--;
			}
			// note that there can be multiple paths
			else if (j - 1 >= 0 && (L[i][j] - L[i][j - 1] == 1)
					&& Math.abs(grid[i][j - 1] - grid[i][j]) == 1)
			{
				path.add(new Pair(i, j - 1));
				j--;
			}
		}

		return path;
	}

	// Function to find maximum length of snake sequence in given matrix
	public static List<Pair> findMaxLengthSnakeSequence(int[][] grid)
	{
		// L[i][j] stores the maximum length of snake sequence
		// ending at cell (i, j)
		int[][] L = new int[grid.length][grid.length];

		// stores maximum length of Snake sequence
		int max_so_far = 0;

		// Pair to store coordinates of snake's tail
		Pair tail = null;

		// process the matrix in bottom-up fashion
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid.length; j++)
			{
				// initialize each element by 0
				L[i][j] = 0;

				// compare current cell with top cell and check absolute
				// difference
				if (i-1 >= 0 && Math.abs(grid[i - 1][j] - grid[i][j]) == 1)
				{
					L[i][j] = L[i - 1][j] + 1;
					if (max_so_far < L[i][j])
					{
						max_so_far = L[i][j];
						tail = new Pair(i, j);
					}
				}

				// compare current cell with left cell and check absolute
				// difference
				if (j-1 >= 0 && Math.abs(grid[i][j - 1] - grid[i][j]) == 1)
				{
					// L[i][j] can be non-zero at this point, hence take max
					L[i][j] = Integer.max(L[i][j], L[i][j - 1] + 1);
					if (max_so_far < L[i][j])
					{
						max_so_far = L[i][j];
						tail = new Pair(i, j);
					}
				}
			}
		}

		// construct maximum length snake sequence
		return constructPath(L, grid, tail);
	}

	// main function
	public static void main(String[] args)
	{
		int[][] grid =
		{
			{ 7, 5, 2, 3, 1 },
			{ 3, 4, 1, 4, 4 },
			{ 1, 5, 6, 7, 8 },
			{ 3, 4, 5, 8, 9 },
			{ 3, 2, 2, 7, 6 }
		};

		List<Pair> path = findMaxLengthSnakeSequence(grid);

		System.out.print("Maximum length Snake sequence : ");

		// use reverse iterator to print the List (from snake head to tail)
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(grid[path.get(i).first][path.get(i).second]
							+ " - ");
		}

		System.out.println("\nLength is: " + (path.size() - 1));
	}
}