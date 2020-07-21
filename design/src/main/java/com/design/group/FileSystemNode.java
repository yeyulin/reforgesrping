package com.design.group;

/**
 * @author yeyulin
 * @description:
 * @date 2020/7/21 17:50
 **/
public abstract class FileSystemNode {
    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
