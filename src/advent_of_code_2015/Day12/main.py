import json


def add_all_numbers(obj) -> int:
    if isinstance(obj, int):
        return obj

    if isinstance(obj, list):
        return sum(add_all_numbers(x) for x in obj)

    if isinstance(obj, dict):
        if "red" in obj.values():
            return 0
        
        return sum(add_all_numbers(v) for v in obj.values())

    return 0

with open("input.txt") as file:
    data = json.load(file)

print(add_all_numbers(data))

