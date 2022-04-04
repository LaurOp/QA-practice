import org.jetbrains.annotations.NotNull;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onStart(@NotNull ITestContext Result) {
        System.out.println("Started test " + Result.getName());
    }
    @Override
    public void onFinish(@NotNull ITestContext Result) {
        System.out.println("Finished test " + Result.getName());
    }



    @Override
    public void onTestStart(@NotNull ITestResult Result) {
        System.out.println(Result.getName() + " test case started");
    }

    @Override
    public void onTestSuccess(@NotNull ITestResult Result) {
        System.out.println("Testcase " + Result.getName() + " is successful");
    }

    @Override
    public void onTestSkipped(@NotNull ITestResult Result) {
        System.out.println("Testcase " + Result.getName() + " is skipped");
    }

    @Override
    public void onTestFailure(@NotNull ITestResult Result) {
        Throwable th = Result.getThrowable();

        if (th != null) {
            System.out.println(th.getMessage());
            System.out.println(th.getMessage());
            Result.setThrowable(null);
        }
        System.out.println("Testcase " + Result.getName() + " FAILED");
    }


}
