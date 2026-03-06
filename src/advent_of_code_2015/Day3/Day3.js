const fs = require("fs");
const readline = require("readline");

function day3 () {
    const data = fs.readFileSync("input.txt", "utf-8");
    // const rl = readline.createInterface({
    //     input: fs.createReadStream('input.txt'),
    //     crlfDelay: Infinity
    // });
    //const map = new Map();
    // let row = 0;
    // let column = 0; 

    // map.set(`${row},${column}`, 1);
    
    // for (const val of data) {
    //     if (val == '>') {
    //         column += 1;
    //     } else if (val == '<') {
    //         column -= 1;
    //     } else if (val == '^') {
    //         row -= 1;
    //     } else {
    //         row += 1;
    //     }

    //     const key = `${row},${column}`;
    //     if (map.has(key)) {
    //         map.set(key, map.get(key) + 1);
    //     } else {
    //         map.set(key, 1);
    //     }

    // }
    const map = new Map();
    let santaRow = 0;
    let santaColumn = 0; 
    let robotRow = 0;
    let robotColumn = 0;
    for (let i = 0; i < data.length; i++) {
        map.set(`${santaRow},${santaColumn}`, 1);
        if (i % 2 == 0) {
            if (data[i] == '>') {
                santaColumn += 1;
            } else if (data[i] == '<') {
                santaColumn -= 1;
            } else if (data[i] == '^') {
                santaRow -= 1;
            } else {
                santaRow += 1;
            }

            const key = `${santaRow},${santaColumn}`;
            if (map.has(key)) {
                map.set(key, map.get(key) + 1);
            } else {
                map.set(key, 1);
            }
        } else {
            if (data[i] == '>') {
                robotColumn += 1;
            } else if (data[i] == '<') {
                robotColumn -= 1;
            } else if (data[i] == '^') {
                robotRow -= 1;
            } else {
                robotRow += 1;
            }

            const key = `${robotRow},${robotColumn}`;
            if (map.has(key)) {
                map.set(key, map.get(key) + 1);
            } else {
                map.set(key, 1);
            }
        }
    }

    console.log(map.size);
}

day3();