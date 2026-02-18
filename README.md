# csv-2-json
A high-performance CSV → JSON converter built completely from scratch using Core Java.

Built a high-performance CSV → JSON streaming converter from scratch using Core Java.
Implemented memory-efficient processing (O(M) space), custom CSV parsing, and automatic type inference without using any third-party libraries.
Great exercise in file I/O optimization and scalable backend design.



.
🏗 Architecture
csv-json-streaming-parser/
│
├── CsvParser.java   # Core streaming engine
├── Main.java                          # Entry point
├── input.csv                          # Sample input
└── output.json                        # Generated output

📦 Example
Input CSV :-
id,name,age,salary,active
1,Dhilipan,26,50000.75,true
2,Arun,28,60000,false

Output JSON :-
[
  {"id":1,"name":"Dhilipan","age":26,"salary":50000.75,"active":true},
  {"id":2,"name":"Arun","age":28,"salary":60000,"active":false}
]

🧪 How To Run
1️⃣ Compile
javac *.java

2️⃣ Run
java Main

Make sure:
input.csv exists in the project directory
Output will be generated as output.json

Why not use libraries?

This project demonstrates understanding of:
File I/O
Memory optimization
JSON formatting
Parsing logic
Type inference
Clean architecture




✔ Numbers remain numbers
✔ Boolean remains boolean
✔ No memory explosion
