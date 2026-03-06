class Solution:
    def __init__(self):
        size = 1000
        self.grid = [[0 for _ in range(size)] for _ in range(size)]

    def process(self, line: str):
        raw = line.strip().split(" ")
        length = len(raw)

        rows = [int(raw[length - 3].split(",")[0]), int(raw[length - 1].split(",")[0])]

        columns = [int(raw[length - 3].split(",")[1]),int(raw[length - 1].split(",")[1])]

        approach = raw[length - 4]

        for row in range(rows[0], rows[1] + 1):
            for column in range(columns[0], columns[1] + 1):
                if approach == "on":
                    self.grid[row][column] += 1
                elif approach == "off":
                    self.grid[row][column] = max(0, self.grid[row][column] - 1)
                else:
                    self.grid[row][column] += 2

    def total_brightness(self):
        return sum(sum(row) for row in self.grid)


solution = Solution()

with open("input.txt") as file:
    for line in file:
        solution.process(line)

print(solution.total_brightness())