package com.smsdispatcher.application;

public record EvaluateSMSContentCommand(String sender, String recipient, String message) {}
