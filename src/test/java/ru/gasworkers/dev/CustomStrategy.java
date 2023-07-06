//package ru.gasworkers.dev;
//
//import org.junit.platform.engine.ConfigurationParameters;
//import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
//import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;
//
//public class CustomStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {
//
//    @Override
//    public int getParallelism() {
//        return 5; // Set the desired parallelism value here
//    }
//
//    @Override
//    public int getMinimumRunnable() {
//        return 5; // Set the desired minimum runnable value here
//    }
//
//    @Override
//    public int getMaxPoolSize() {
//        return 5; // Set the desired max pool size value here
//    }
//
//    @Override
//    public int getCorePoolSize() {
//        return 5; // Set the desired core pool size value here
//    }
//
//    @Override
//    public int getKeepAliveSeconds() {
//        return 30; // Set the desired keep-alive seconds value here
//    }
//
//    @Override
//    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
//        return this;
//    }
//}
