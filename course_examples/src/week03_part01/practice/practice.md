Week 3 Part 1 Practice Problems
========================

Learning Objectives
-------------------
- Comparing Growth Rate
- Big(Oh)


Problem 1
---------

**Order the following functions by grown rate. Indicate which functions grow at the same rate.**


a) *N*<br>
b) *N*<sup>1/2</sup><br>
c) *N*<sup>1.5</sup><br>
d) *N*<sup>2</sup><br>
e) *N* log *N*<br>
f) *N* log log *N*<br>
g) *N* log<sup>2</sup> *N*<br>
h) *N* log(*N*<sup>2</sup>)<br>
i) 2 / *N*<br>
j) 2<sup>*N*</sup><br>
k) 2<sup>*N*/2</sup><br>
l) 37<br>
m) *N*<sup>2</sup> log *N*<br>
o) log *N*<br>
p) *N*<sup>3</sup><br>



Problem 2
---------

**For each of the following six program fragments:**<br>
1. **Give an analysis of the running time (Big-Oh will do).**<br>
2. **Implement the code in Java, and give the running time for several values of N.**<br>
3. **Compare your analysis with the actual running times.**



a)
```java
sum = 0;
for( i = 0; i < n; i++ )
	sum++;
```

b)
```java
sum = 0;
for( i = 0; i < n; i++ )
	for( j = 0; j < n; j++ )
		sum++;
```

c)
```java
sum = 0;
for( i = 0; i < n; i++ )
	for( j = 0; j < n * n; j++ )
		sum++;
```

d)
```java
sum = 0;
for( i = 0; i < n; i++ )
	for( j = 0; j < i; j++ )
		sum++;
```

e)
```java
sum = 0;
for( i = 0; i < n; i++ )
	for( j = 0; j < i * i; j++ )
		for( k = 0; k < j; k++ )
			sum++;
```

f)
```java
sum = 0;
for( i = 1; i < n; i++ )
{
	for( j = 1; j < i * i; j++ )
	{
		if( j % i == 0 )
			for( k = 0; k < j; k++ )
				sum++;
	}
}
```
