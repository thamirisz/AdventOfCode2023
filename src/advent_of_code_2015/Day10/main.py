def look_and_say(s: str) -> str:
    result = []
    count = 1

    for i in range(1, len(s)):
        if s[i] == s[i - 1]:
            count += 1
        else:
            result.append(str(count))
            result.append(s[i - 1])
            count = 1

    # last group
    result.append(str(count))
    result.append(s[-1])

    return "".join(result)


def main(input_str: str, times: int = 40) -> int:
    current = input_str

    for _ in range(times):
        current = look_and_say(current)

    return len(current)


print(main("1113222113", 50))

# def main (input: str) -> int:
#     map = {}
#     result = 0
#     for _ in 40:
#         for i in range(len(input)):
#             key = int(input[i])
#             map[key] = map.get(key, 0) + 1
#             if i > 0 and key != int(input[i - 1]):
#                 previous_key = int(input[i - 1])
    
#                 if result == 0:
#                     result += map[previous_key] * 10
#                     result += previous_key
#                 else:
#                     result *= 10
#                     result += map[previous_key]
#                     result *= 10
#                     result += previous_key
#                 del map[previous_key]
    
#     #post process
#     for key, value in map.items():
#         result *= 10
#         result += value
#         result *= 10
#         result += key

#     return result


# print(main("111221"))
