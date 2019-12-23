import java.util.logging.Logger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestCodeGenerator {

    private static final Logger logger = Logger.getLogger(TestCodeGenerator.class.getName());

    public static final String CURRENT_FOLDER = String.valueOf(Paths.get("testGenerator"));

    public static Path tempDir;

    public static void main(String[] args) throws Exception {
        tempDir =  Files.createTempDirectory("testProject");
        int numberOfClassesToGenerate = 1000;
        generateClassUnderTest(numberOfClassesToGenerate);
        generateTestClasses(numberOfClassesToGenerate);
    }

    public static void generateClassUnderTest(int numberOfClassesToGenerate) throws Exception {
        Path pomFile = Paths.get(CURRENT_FOLDER, "pom.xml");
        Files.copy(pomFile, Path.of(tempDir.toString(), "pom.xml"));

        Path targetSourceFolder = Paths.get(tempDir.toString(), "src/main/java/x");
        Files.createDirectories(targetSourceFolder);

        Path template = Paths.get(CURRENT_FOLDER, "ClassUnderTestTemplate.java");
        String templateClass = Files.readString(template);

        for (int i=0; i< numberOfClassesToGenerate; i++) {
            String classUnderTestName = "ClassUnderTest" + i;
            String newClass = templateClass.replace("ClassUnderTestTemplate", classUnderTestName);
            Path fullPathToNewClass = Paths.get(targetSourceFolder.toString(), "ClassUnderTest" + i + ".java");
            Files.write(fullPathToNewClass, newClass.getBytes(StandardCharsets.UTF_8));
            logger.info(String.format("created %s", fullPathToNewClass.toString()));
        }
    }

    public static void generateTestClasses(int numberOfClassesToGenerate) throws Exception {
        Path targetSourceFolder = Paths.get(tempDir.toString(), "src/test/java/x");
        Files.createDirectories(targetSourceFolder);

        Path template = Paths.get(CURRENT_FOLDER, "ClassUnderTestTestTemplate.java");
        String templateClass = Files.readString(template);

        for (int i=0; i< numberOfClassesToGenerate; i++) {
            String classUnderTestName = "ClassUnderTest" + i;
            String testClassName = "ClassUnderTest" + i + "Test";
            String newClass = templateClass.replaceAll("ClassUnderTestTestTemplate", "ClassUnderTest" + i + "Test")
                    .replaceAll("ClassUnderTestTemplate", classUnderTestName);
            Path fullPathToNewClass = Paths.get(targetSourceFolder.toString(), testClassName + ".java");
            Files.write(fullPathToNewClass, newClass.getBytes(StandardCharsets.UTF_8));
            logger.info(String.format("created %s", fullPathToNewClass.toString()));
        }
    }
}

