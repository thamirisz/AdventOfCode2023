const fs = require("fs");
const readline = require("readline");

function day5() {
    const rl = readline.createInterface({
        input: fs.createReadStream('input.txt'),
        crlfDelay: Infinity
    });

    // const vowelSet = new Set(['a', 'e', 'i', 'o', 'u']);
    // const notAllowed = new Set(["ab", "cd", "pq", "xy"]);

    var totalNiceWord = 0;

    // rl.on('line', (line) => {
    //     let totalVowel = 0;
    //     let repetitiveLetter = false;
    //     let hasForbidden = false;
    //     for (let i = 0; i < line.length; i++) {
    //         if (vowelSet.has(line[i])) {
    //             ++totalVowel;
    //         }
    //         if (i > 1) {
    //             const pair = line[i - 1] + line[i];
    //             if (notAllowed.has(pair)) {
    //                 hasForbidden = true;
    //             }
    //             if (line[i] === line[i - 1]) {
    //                 repetitiveLetter = true;
    //             }
    //         }
    //     }

    //     if (!hasForbidden && totalVowel >= 3 && repetitiveLetter) {
    //         totalNiceWord++;
    //     }

    // });

    rl.on ('line', (line) => {
        if (repeatingLetter(line) && repeatedMap(line)) {
            totalNiceWord++;
        }
    });

    rl.on('close', () => {
        console.log("Total nice words:", totalNiceWord);
    });
}

// function hasRepeatedPair(str) {
//         const pairMap = new Map();
//         for (let i = 0; i < str.length - 1; i++) {
//             const pair = str[i] + str[i + 1];
//             if (pairMap.has(pair)) {
//                 const prevIndex = pairMap.get(pair);
//                 if (i - prevIndex > 1) return true;
//             } else {
//                 pairMap.set(pair, i);
//             }
//         }
//         return false;
//     }

// function hasRepeatingLetter(str) {
//     for (let i = 0; i < str.length - 2; i++) {
//         if (str[i] === str[i + 2]) return true;
//     }
//     return false;
// }

function repeatingLetter(line) {
    for (let i = 0; i < line.length - 2; i++) {
        if (line[i] === line[i + 2]) {
            return true;
        }
    }
    return false;
}

function repeatedMap(line) {
    let map = new Map();
    for (let i = 0; i < line.length - 1; i++) {
        const pair = line[i] + line[i + 1];
        if (map.has(pair)) {
            const previousIndex = map.get(pair);
            if (i - previousIndex > 1)
                return true;
        } else {
            map.set(pair, i);
        }

    }
    return false;
}

day5();