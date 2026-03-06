const fs = require("fs");
const readline = require("readline");

async function test() {
    const rl = readline.createInterface({
        input: fs.createReadStream('sample.txt'),
        crlfDelay: Infinity
    });

    let res = 0;
    let position = 0;

    for await (const line of rl) {
        for (const parenthese of line) {
            position++;
            if (parenthese == "(") {
                res++;
            } else {
                res--;
            }

            if (res == -1) {
                return position;
            }
        }
    }

    return res;
}

async function main() {
    const ss = await test();
    console.log(ss);
}

main();