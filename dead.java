class Q
{
        int num;
       static boolean value=false;
        synchronized void put()
        {
                if(value==false)
                {
                        try{
                                wait();
                        }
                        catch(Exception e)
                        {}
                        
                }
                else
                {
                System.out.println("put:"+num);
                value=false;
                notify();
                }
        }
        synchronized void get(int a)
        {
                if(value==true)
                {
                     try{   wait();}
                catch(Exception e){}
                }
                else
                {
                        System.out.println("get"+a);
                        num=a;
                        value=true;
                        notify();
                }
                
        }
}
class producer extends Thread
{
Q q;
producer(Q _q)
{
        q=_q;
}
public void run()
{
        for(int i=0;i<=10;i++)
        {
        q.get(i);
        }
}

}
class consumer extends Thread
{
        Q q;
        consumer(Q _q)
        {
                q=_q;
        }
        public void run()
        {
        for(int i=0;i<=10;i++)
        {
                q.put();
        }
        }
}
class test
{
public static void main(String args[])
{
        Q q=new Q();
        producer p=new producer(q);
        consumer c=new  consumer(q);
        p.start();
        c.start();
        
 }       


}
