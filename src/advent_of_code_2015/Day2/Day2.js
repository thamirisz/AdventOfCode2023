const fs = require("fs");
const readline = require("readline");

async function day2() {
    const rl = readline.createInterface({
        input: fs.createReadStream('input.txt'),
        crlfDelay: Infinity
    });

    var totalSum = 0;
    for await (const line of rl) {
        //question 2
        const [length, width, height] = line.split("x").map(Number).sort(
            function (a, b) {
                return a - b;
            }
        );
        const ribbon = (2 * length) + (2 *  width);
        const bow = length * width * height;
        totalSum += ribbon + bow;

    }

    //question 1 
    // const lengthSide = length * width;
    // const widthSide = width * height;
    // const heightSide = height * length;
    // const minimumSide = Math.min(lengthSide, Math.min(widthSide, heightSide));
    // totalSum += minimumSide + (2 * lengthSide) + (2 * widthSide) + (2 * heightSide);

    return totalSum;
}

async function main() {
    const ss = await day2();
    console.log(ss);
}

main();