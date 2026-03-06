def increment_password(input: str) -> str:
    char_list = input
    for i in range(len(input) - 1, -1, -1): 
        if char_list[i] == 'z':
            char_list[i] = 'a'
            i -= 1
        else:
            char_list[i] += 1
            break
    
    return "".join(char_list)

def is_valid_password(input: str) -> bool: 
    char_list = input
    for i in range(len(input)):
        if input[i] == 'i' or input[i] == 'o' or input[i] == 'l':
            return False
    
    has_straight = False
    for i in range(len(input) - 2):
        if input[i + 1] == input[i] + 1 and input[i + 2] == input[i] + 2:
            has_straight = True
            break
    
    if has_straight is False:
        return False
    
    pairCount = 0
    for i in range(len(input) - 1):
        if input[i] == input[i + 1]:
            pairCount += 1
            i += 1
            
    return  pairCount >= 2




password = "cqjxjnds"

while is_valid_password is not True: 
    password = increment_password(password)

print(password)