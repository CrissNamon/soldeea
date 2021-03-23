## What is it?
<b>SOLDEEA</b> - Encryption algorithm based on system of linear diophantine equations.
<br>
This algorithm uses the idea about infinite solutions of system, where number of variables in diophantine equations is more than number of equations.
<br>
The main feature of algorithm is resistance to quantum algorithm attacks, such as Shor's or Grover's algorithm.
<br>
This repository contains simple realisation of algorithm in Java.
## How it works?
If user has private encryption key, system can be solved easily using Gauss method, for example.
<br>
But if user has only encrypted information, system has infinite solutions, so plain data can't be recovered in finite time interval.
<br>
## About
This algorithm was developed as part of conference in Kuban State University, Krasnodar city.
<br>
Author: <b>Rassokhin Danila</b>, student of 3 course
<br>
Encryption math model:
<br>
![Encryption model](https://github.com/CrissNamon/soldeea/blob/master/encryption_model.png "Encryption math model")
<br>
Decryption math model:
<br>
![Decryption model](https://github.com/CrissNamon/soldeea/blob/master/decryption_model.png "Decryption math model")