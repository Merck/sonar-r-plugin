package com.msd.gin.common.sonar;

import com.msd.gin.common.sonar.model.LintRIssue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LintRSensorTest {

    @TempDir
    Path baseDir;

    @Test
    void readSuccessfullyEmptyLintrOutputFile() throws Exception {
        FileSystem fs = new DefaultFileSystem(baseDir);

        File f = baseDir.resolve("lintr_output.json").toFile();
        assertTrue(f.createNewFile(), "Cannot create file");

        LintRSensor sensor = new LintRSensor(fs);
        List<LintRIssue> result = sensor.readLintrOutputFile(f.getAbsolutePath());
        assertTrue(result.isEmpty(), "There should not be any issue found in empty result file");
    }

}