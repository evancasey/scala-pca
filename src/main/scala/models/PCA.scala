package models

import breeze.linalg._
import breeze.math._
import breeze.numerics._
import breeze.stats._

trait PCA {
  def compute(trainX: DenseMatrix[Double], numComponents: Int): DenseMatrix[Double]

}


object EigenPCA extends PCA {
  import PCA._

  // Goal: compute eigenvectors of the n x n covariance matrix X^T * X
  def compute(trainX: DenseMatrix[Double], numComponents: Int): DenseMatrix[Double] = {
    val rda = adjustColumnMean(trainX)

    // TODO: reduce dims!
    val er, _ , ev = eig(pairwiseColumnCovariance(rda))
    val rfv = ev.eigenvectors.toDenseMatrix
    rfv.t * rda
  }
}

object FastEigenPCA extends PCA {

  // Goal: compute eigenvectors of the X*X^T, then multiply by X^T
  def compute(trainX: DenseMatrix[Double], numComponents: Int): DenseMatrix[Double] = {
    ???
  }
}

object SVDPCA extends PCA {
  def compute(trainX: DenseMatrix[Double], numComponents: Int): DenseMatrix[Double] = {
    ???
  }

}

object PCA {
  def covariance(x: DenseVector[Double], y: DenseVector[Double]): Double = {
    val varX = x.map { i => i - mean(x) }
    val varY = y.map { j => j - mean(y) }
    sum(varX :* varY) / (x.length - 1.0) // element-wise multiplication
  }

  def pairwiseColumnCovariance(x: DenseMatrix[Double]): DenseMatrix[Double] = {
    val cols = x(::,*) // convert to column broadcast form
    cols.map { c1 =>
      val colPairs = cols.map { c2 => (c1,c2) }
      val covs = colPairs.map { case (c1,c2) => covariance(c1,c2) }.toDenseVector
      covs
    }
  }

  def adjustColumnMean(x: DenseMatrix[Double]): DenseMatrix[Double] = {
    val colMeans = mean(x(::,*)) // convert to column broadcast form
    x(*,::) - colMeans.toDenseVector // switch to row broadcast form before sub
  }
}

