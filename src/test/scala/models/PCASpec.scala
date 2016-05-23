package models

import breeze.linalg._
import org.scalatest._
import Matchers._

class PCASpec extends WordSpec {
  "EigenPCA" should {
    "generate a correct output matrix with no dimensionality reduction" in {
      val x = DenseMatrix((1.0,0.0),(0.0,1.0))

      val fd = EigenPCA.compute(x, x.cols)
      val rda = PCA.adjustColumnMean(x)
      fd * inv(rda)).t
    }
  }

}
