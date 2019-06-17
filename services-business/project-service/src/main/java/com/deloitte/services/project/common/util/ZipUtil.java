package com.deloitte.services.project.common.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

/**
 * Created by lixin on 06/03/2019.
 */
public class ZipUtil {

    public static void zip(String srcDir, String targetFile) throws IOException {
        try (OutputStream fos = new FileOutputStream(targetFile);
             OutputStream bos = new BufferedOutputStream(fos);
             ArchiveOutputStream aos = new ZipArchiveOutputStream(bos)) {
            File srcDirFile = new File(srcDir);
            Path dirPath = Paths.get(srcDirFile.getAbsolutePath());
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    ArchiveEntry entry = new ZipArchiveEntry(dir.toFile(), dirPath.relativize(dir).toString());
                    aos.putArchiveEntry(entry);
                    aos.closeArchiveEntry();
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    ArchiveEntry entry = new ZipArchiveEntry(
                            file.toFile(), dirPath.relativize(file).toString());
                    aos.putArchiveEntry(entry);
                    IOUtils.copy(new FileInputStream(file.toFile()), aos);
                    aos.closeArchiveEntry();
                    return super.visitFile(file, attrs);
                }
            });
        }
    }

    public static void unzip(String zipFileName, String destDir) {
        File zipFile = new File(zipFileName);
        try (InputStream fis = Files.newInputStream(Paths.get(zipFile.getAbsolutePath()));
             InputStream bis = new BufferedInputStream(fis);
             ArchiveInputStream ais = new ZipArchiveInputStream(bis)) {

            ArchiveEntry entry;
            while (Objects.nonNull(entry = ais.getNextEntry())) {
                if (!ais.canReadEntryData(entry)) {
                    continue;
                }

                String name = filename(destDir, entry.getName());
                File f = new File(name);
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        f.mkdirs();
                    }
                } else {
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                        IOUtils.copy(ais, o);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzip(File zipFile, String destDir) {
        try (InputStream fis = Files.newInputStream(Paths.get(zipFile.getAbsolutePath()));
             InputStream bis = new BufferedInputStream(fis);
             ArchiveInputStream ais = new ZipArchiveInputStream(bis)) {

            ArchiveEntry entry;
            while (Objects.nonNull(entry = ais.getNextEntry())) {
                if (!ais.canReadEntryData(entry)) {
                    continue;
                }

                String name = filename(destDir, entry.getName());
                File f = new File(name);
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        f.mkdirs();
                    }
                } else {
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                        IOUtils.copy(ais, o);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzip(InputStream fis, String destDir) {
        try {
            InputStream bis = new BufferedInputStream(fis);
            ArchiveInputStream ais = new ZipArchiveInputStream(bis);

            ArchiveEntry entry;
            while (Objects.nonNull(entry = ais.getNextEntry())) {
                if (!ais.canReadEntryData(entry)) {
                    continue;
                }

                String name = filename(destDir, entry.getName());
                File f = new File(name);
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        f.mkdirs();
                    }
                } else {
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                        IOUtils.copy(ais, o);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String filename(String destDir, String name) {
        return destDir + File.separator + name;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(ZipUtil.class.getClassLoader().getResource("simsun.ttf").getPath());
        System.out.println(new ClassPathResource("simsun.ttf").getFile().getAbsolutePath());
        String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
        System.out.println("jarPath:" + jarPath);
    }


}
