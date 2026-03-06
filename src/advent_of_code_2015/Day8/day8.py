import ast

class Solution:
    def __init__(self):
        self.total_string = 0
        self.total_valid_chars = 0

    def process1 (self, raw_line: str):
        line = raw_line.strip()
        self.total_string += len(line)

        memory_string = ast.literal_eval(line)
        #print(memory_string)
        self.total_valid_chars += len(memory_string)
    
    def result1 (self) -> int:
        return self.total_string - self.total_valid_chars

    def process2 (self, raw_line: str):
        line = raw_line.strip()
        self.total_string += len(line)
        #"" 
        encoded = '"' + line.replace('\\', '\\\\').replace('"', '\\"') + '"'
        self.total_valid_chars += len(encoded)
    
    def result2(self) -> int: 
        return self.total_valid_chars - self.total_string

solution = Solution()

with open("input.txt") as file:
    for line in file:
        solution.process2(line)
    
print(solution.result2())

x = '\\\\'
print(x)
print(len(x))