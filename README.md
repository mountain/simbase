Simbase: A vector similarity database
======================================

Simbase is a redis-like vector similarity database. You can add, get, delete
vectors to/from it, and then retrieve the most similar vectors within one vector
set or between two vector sets.

Release
--------

Current version is [v0.1.0](https://github.com/mountain/simbase/releases/tag/v0.1.0).

Build status
-------------
[![Build Status](https://secure.travis-ci.org/mountain/simbase.png)](http://travis-ci.org/mountain/simbase)

Concepts
--------

Simbase use a concept model as below:

                       + - - - +
          +----------->| Basis |<------------------+
          |  belongs   + _ _ _ +      belongs      |
          |                                        |
          |                                        |
    + - - - - - +        source           + - - - - - - - -+ 
    | VectorSet |<------------------------| Recommendation |
    + - - - - - +                         + - - - - - - - -+
          ^              target                    |
          |________________________________________|

* Vector set: a set of vectors
* Basis: the basis for vectors, vectors in one vector set have same basis
* Recommendation: a one-direction binary relationship between two vector sets which have the same basis

A real example follow the model below:

         + - - - - - +                 + - - - - - - - -+ 
    +--->|  Articles |<----------------|  User Profiles |
    |    + - - - - - +                 + - - - - - - - -+
    |          |
    +----------+

This graph shows

* recommend article by article (recommend from article to article)
* recommend article by user profile (recommend from user profile to article)

How to build and start
-----------------------

To build the project, you need install leiningen first, and then

  > cd SIMBASE_HOME
  
  > lein uberjar

After the uberjar is created, you can start the system

  > cd SIMBASE_HOME
  
  > bin/start

How to connect to Simbase
--------------------------

You can use redis-cli directly for administration tasks.

Or you can use redis client bindings in different language directly in a programming way.

Python example

``` python
import redis

dest = redis.Redis(host='localhost', port=7654)
dest.execute_command('bmk', 'ba', 'a', 'b', 'c')
dest.execute_command('vmk', 'ba', 'va')
dest.execute_command('rmk', 'va', 'va', 'cosinesq')
```

Node.js example

``` javascript
var redis = require("redis"), client = redis.createClient(7654, 'localhost');

client.send_command('bmk', ['ba', 'a', 'b', 'c'])
client.send_command('vmk', ['ba', 'va'])
client.send_command('rmk', ['va', 'va', 'cosinesq'])
```

A general application case
---------------------------

For example, we need to recommend articles to users, we may follow below steps:

Setup

    > bmk b2048 t1 t2 t3 ... t2047 t2048
    > vmk b2048 article
    > vmk b2048 userprofile
    > rmk userprofile article cosinesq

Fill data

    > vadd article 1 0.11 0.112 0.1123...
    > vadd article 2 0.21 0.212 0.2123...
    ...    

    > vadd userprofile 1 0.11 0.112 0.1123...
    > vadd userprofile 2 0.21 0.212 0.2123...
    ...

Query

    > rrec userprofile 2 article

All commands are explained in next section.

Core commands
--------------
Then you can use redis-cli to connect to simbase directly

Basis related

*   blist

    > blist
    
    List all basis in system

*   bmk basisname components...

    > bmk b512 universe time space human animal plant...
    
    Create a basis
	
*   brev basisname components...

    > brev b512 plant animal human space time universe...
    
    Revise a basis
    
Vector set related

*   vlist basisname

    > vlist b512
    
    List all vector set with one basis

*   vmk basisname vecsetname

    > vmk b512 article
    
    Create a vector set

*   vget vecsetname vecid

    > vget article 12345678
    
    Get the vector for the article with id 12345678

*   vadd vecsetname vecid components...

    > vadd article 12345678 0.1 0.12 0.123 0.1234 0.12345 0.123456...
    
    add the value for the article vector with id 12345678

*   vset vecsetname vecid components...

    > vset article 12345678 0.1 0.12 0.123 0.1234 0.12345 0.123456...
    
    set the value for the article vector with id 12345678

*   vacc vecsetname vecid components...

    > vacc article 12345678 0.1 0.12 0.123 0.1234 0.12345 0.123456...
    
    accumulate the value for the article vector with id 12345678

*   vrem vecsetname vecid

    > vrem article 12345678
    
   remove the vector with id 12345678 from article vector set 

Recommendation related

*   rlist vecsetname

    > rlist article
    
    List all recommendation targets with the inputed vecset as source

*   rmk vecsetname1 vecsetname2 funcscore

    > rmk userprofile article cosinesq
    
    Create a recommendation to article by userprofile and it use cosinesq as score function.
    Currently score functions you can choice are: 'cosinesq' and 'jensenshannon' 

*   rrec vecsetname1 vecid vecsetname2

    > rrec userprofile 87654321 article
    
    Recommend articles for user 87654321

Limitations
------------

### Assumptions on vectors

Although Simbase can store arbitrary vectors, but score functions may apply some constraints on vectors.

For example, if you adopt "jensenshannon" as your score function, you should assure your vector is a
probability distribution, i.e. the sum of all components equals to one.

### Performance consideration

The write operation is handled in a single thread per basis, and comparison between any two vectors is needed,
so the write operation is scaled at O(n).

We had a non-final performance test for the dense vectors on an i7-cpu Macbook, it can easily handle 100k
1k-dimensional vectors with each write operation in under 0.14 sec; and if the linear scale ratio can hold, 
it means Simbase can handle 700k dense vectors with each write operation in under 1 sec.

Since the data is all in memory, the read operation is pretty fast.

We are still in the process of tuning the performance of the sparse vectors.

Licenses
---------

Simbase is dual licensed under the Apache License 2.0 and
Eclipse Public License 1.0. Simbase is free for commercial use
and distribution under the terms of either license.

Special thanks
---------------

Special thanks for Feng Sheng, we borrowed lots of code from his
great project http-kit ( https://github.com/http-kit/http-kit/ ).

Also thanks for Kunwei Zhang from Tsinghua Univ. for his smart idea.  

Contributors
-------------

* Mingli Yuan ( https://github.com/mountain )
* Wanjian Wu ( https://github.com/jseagull )
* Yang Zhang ( https://github.com/zmouren )
* Jianjiang Zhu ( https://github.com/zjjott )
* Jiacai Liu ( https://github.com/jiacai2050 )




