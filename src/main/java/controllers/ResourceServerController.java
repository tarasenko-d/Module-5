package controllers;

import resources.TestResource;

public class ResourceServerController implements ResourceServerControllerMBean {
    private TestResource testResource;

    public ResourceServerController(TestResource testResource) {
        this.testResource=testResource;
    }

    @Override
    public String getName(){
        return testResource.getName();
    }
    public int getAge(){
        return testResource.getAge();
    }
    public void setName(String name){
        testResource.setName(name);
    }
    public void setAge(int age){
        testResource.setAge(age);
    }
}
