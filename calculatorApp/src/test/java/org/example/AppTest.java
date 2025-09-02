package org.example;

import org.junit.platform.console.ConsoleLauncher;

public class AppTest {
    public static void main(String[] args) {
        ConsoleLauncher.main(new String[]{
                "execute",
                "--select-package", "org.example",
                "--details", "tree"
        });
    }
}