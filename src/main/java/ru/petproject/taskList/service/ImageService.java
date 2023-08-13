package ru.petproject.taskList.service;

import ru.petproject.taskList.entity.task.TaskImage;

public interface ImageService {
    String upload(TaskImage taskImage);
}
