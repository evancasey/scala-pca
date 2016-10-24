scala-pca
---------
A barebones implemetation of principal Components Analysis in Scala

Usage
-----
```scala
import breeze.linalg._
import models.EigenPCA

// initialize a matrix we'd like to use PCA on
val x = DenseMatrix((1.0,0.0),(0.0,1.0))

// do the thing
val res = EigenPCA.compute(x, x.cols)
```
