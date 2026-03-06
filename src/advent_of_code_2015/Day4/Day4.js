const fs = require('fs');
const crypto = require('crypto');

function day4 () {
    const secretKey = fs.readFileSync('sample.txt');
    //const secretKey = "abcdef";
    let number = 0;

    while (true) {
    const hash = crypto
        .createHash("md5")
        .update(secretKey + number)
        .digest("hex");
    
    if (hash.startsWith("000000")) {
        console.log("Answer:", number);
        break;
    }
    
    number++;
    }
}

day4();