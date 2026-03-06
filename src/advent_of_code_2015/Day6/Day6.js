const fs = require("fs");
const readline = require("readline");

function day6() {
    const rl = readline.createInterface({
        input: fs.createReadStream('input.txt'),
        crlfDelay: Infinity
    });
    const size = 1000;

    const matrix = Array.from({length: size}, () => 
        new Array(size).fill(0)
    );

    rl.on('line', (line) => {
        const raw = line.split(" ");
        const length = raw.length;
        const rows = [
            Number(raw[length - 3].split(",")[0].trim()),
            Number(raw[length - 1].split(",")[0].trim())
        ];
        const columns = [
            Number(raw[length - 3].split(",")[1].trim()),
            Number(raw[length - 1].split(",")[1].trim())
        ];
        const approach = raw[length - 4];

        for (let row = rows[0]; row <= rows[1]; row++) {
            for (let column = columns[0]; column <= columns[1]; column++) {
                if (approach == "on") {
                    matrix[row][column] += 1;
                } else if (approach == "off") {
                    matrix[row][column] = Math.max(0, matrix[row][column] - 1);
                } else {
                    matrix[row][column] += 2;
                }
            }
        }
    });

    rl.on('close', () => {
        var result = 0;
        for (let i = 0; i < 1000; i++) {
            for (let j = 0; j < 1000; j++) {
 
                result += matrix[i][j];
                
            }
        }
        console.log(result);
    });

}

day6();