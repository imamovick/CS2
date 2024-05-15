/*
Chiana and Kenan
 */

public class Integrator extends Thread {

    static private long INTERVALS = 1 << 50;

    // The instance variables for each instance of Thread.
    private int id;
    private UnivariateFunction f;
    private double a;
    private double b;
    private double[] results;

    public Integrator(int id, UnivariateFunction f, double a, double b, double[] results) {
        this.id = id;
        this.f = f;
        this.a = a;
        this.b = b;
        this.results = results;
    }

    public void run() {
        results[id] = integrate(f, a, b);
    }

    static private double integrate(UnivariateFunction f, double a, double b) {
        double step = (b - a)/INTERVALS;
        double integral = 0.0;
        for (int i = 0; i < INTERVALS; ++i) {
            integral += f.eval(a + (i + 0.5)*step) * step;
        }
        return integral;
    }

    static private double parallelIntegrate(UnivariateFunction f, double a, double b, int numThreads) throws InterruptedException {
        Integrator[] t = new Integrator[numThreads];
        double[] results = new double[numThreads];

        double sub = (b-a)/numThreads;
        double start = a;
        double stop = start + sub;

        for (int id = 0; id < numThreads; ++id) {
            t[id] = new Integrator(id, f, start, stop, results);
            t[id].start();
            start = stop;
            stop = start + sub;
        }

        for (int id = 0; id < numThreads; ++id) {
            t[id].join();
        }

        double integral = 0.0;
        for (int i = 0; i < results.length; ++i) {
            integral += results[i];
        }

        return integral;
    }

    static public void main(String[] unused) throws InterruptedException {
        double integral;
        double start;
        double end;
        Stopwatch timer = new Stopwatch();

        StdOut.println("The integral of x squared in the interval [0, 10].");
        start = timer.elapsedTime();
        integral = integrate(x -> x*x, 0, 10);
        end = timer.elapsedTime();
        StdOut.printf("Serial:   integral = %.15f (%.1f milliseconds)\n", integral, (end - start)*1000);

        int numThreads = 4;
        start = timer.elapsedTime();
        integral = parallelIntegrate(x -> x*x, 0, 10, numThreads);
        end = timer.elapsedTime();
        StdOut.printf("Parallel: integral = %.15f (%.1f milliseconds, %d threads)\n", integral, (end - start)*1000, numThreads);
    }

}
