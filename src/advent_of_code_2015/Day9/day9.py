from itertools import permutations

# array = [1, 2, 3]
# for p in permutations(array):
#     print(p)

def solve() -> int:
    citySet= set()
    distanceMap = map()

    with open("input.txt") as file:
        for raw_line in file:
            line = raw_line.strip().split(" ")
            city1 = line[0]
            city2 = line[2]
            distance = int(line[4])

            citySet.add(city1)
            citySet.add(city2)

            if city1 not in distanceMap:
                distanceMap[city1] = {}
            if city2 not in distanceMap:
                distanceMap[city2] = {}

            distanceMap[city1][city2] = distance
            distanceMap[city2][city1] = distance

        #max_value
        #min_distance = float('inf')
        max_distance = float('-inf')
        for city in permutations(citySet):
            #city is a permutation of an array
            #print(city)
            cur_total = 0
            for i in range(len(city) - 1):
                cur_total += distanceMap[city[i]][city[i+1]]

            #min_distance = min(min_distance, cur_total)
            max_distance = max(max_distance, cur_total)

        return max_distance
# tc: O(n!) 
print(solve())
            
    