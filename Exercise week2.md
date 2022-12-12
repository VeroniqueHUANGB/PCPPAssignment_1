## Exercise week2

##### Exercise 2.1

1. As you can see from ReadWriteMonitor.java
2. It is not fair. Because if no writers thread run successfully, the flag variable 'writer' will always be false, and then there can only be reader threads. But once we change the 'writer' to true if there's an writer request, then the code is fair. There won't be more reader thread if there's an writer waited in the list.

##### Exercise 2.2

1. It loops forever. Eventhough the mi.value was changed by the main thread, for t thread, the value of mi is still 0, thus it loops forever.
2. ![截屏2022-12-02 17.53.33](/Users/zq2lii/Desktop/截屏2022-12-02 17.53.33.png)

​	As you can see now, the t thread always terminates. Because I add lock on both get and set method, which means finally t thread needs to wait for the main thread terminates, and once main thread terminates, the value of mi is changed, so that t thread will terminate.

3. No. 

4. t always terminate in this case.

   

##### Exercise 2.3

1. ![截屏2022-12-02 18.11.33](/Users/zq2lii/Desktop/截屏2022-12-02 18.11.33.png)

As the picture shown above, there's a race condition.

2. For static method, the Thread will acquire a class level lock of a java class, such that only one thread can act on the static synchronized method. But for instance method, the thread acquire a  instance-level lock. When a instance try to apply one static method in one thread, and one instance method in the other thread, it is not mutable, because they use different locks.

3. ![截屏2022-12-02 18.31.13](/Users/zq2lii/Library/Application Support/typora-user-images/截屏2022-12-02 18.31.13.png)

   When we add a lock on the class level for instance method, then 2 lock will be one the same level, actually they become the same lock right now. Thus there won't be a race condition now.