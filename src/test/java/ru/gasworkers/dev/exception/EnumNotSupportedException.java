package ru.gasworkers.dev.exception;

public final class EnumNotSupportedException extends RuntimeException {

    public EnumNotSupportedException(Enum<?> enumMember) {
        super("Enum with type [" + enumMember.name() + "] not supported");
    }

}
