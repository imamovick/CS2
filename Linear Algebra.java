class LinearAlgebra {

    static double magnitude(double[] v) {
        double x = 0;
        for (int i = 0; i < v.length; i++) {
            x = x + v[i]*v[i];
        }
        double result = Math.sqrt(x);
        return result;
    }

    static double[] sum(double[] v, double[] w) {
        double[] result = new double [v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i]+w[i];
        }
        return result;
    }


    static double[] difference(double[] v, double[] w) {
        double[] result = new double [v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i]-w[i];
        }
        return result;
    }

    static double[] elementwiseProduct(double[] v, double[] w) {
        double[] result = new double [v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i]*w[i];
        }
        return result;
    }

    static double dotProduct(double[] v, double[] w) {
        double result = 0;
        for (int i = 0; i < v.length; i++) {
                result = result + v[i]*w[i];
        }
        return result;
    }

    static int[] dimensions(double[][] m) {
        int[] result = new int[2];
        result[0] = m.length;
        result[1] = m[0].length;
        return result;
    }

    static double[][] sum(double[][] m, double[][] n) {
        double[][] result = new double [m.length][n.length];
        for (int i= 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                result[i][j] = 0;
                result[i][j] = m[i][j]+n[i][j];
            }
        }
        return result;
    }

    static double[][] elementwiseProduct(double[][] m, double[][] n) {
        double[][] result = new double [m.length][n.length];
        for (int i= 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                result[i][j] = 0;
                result[i][j] = m[i][j]*n[i][j];
            }
        }
        return result;
    }

    static double[][] transpose(double[][] m) {
        double[][] result = new double [m[0].length][m.length];
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                result [i][j] = m[j][i];
            }
        }
        return result;
    }

    static double[][] product(double[][] m, double[][] n) {
        double[][] result = new double[m.length][n[0].length];
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < n[0].length; j++) {
                    result[i][j] = 0;
                    for (int k = 0; k < n.length; k++) {
                        result[i][j] = result[i][j] + m[i][k] * n[k][j];
                    }
                }
            }
        return result;
    }
}