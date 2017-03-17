package ebaeapp.com.ebae.roll;


import com.yelp.fusion.client.models.Business;

/**
 * Created by thiba on 05/03/2017.
 */

@FunctionalInterface
public interface BusinessRunnable {
  void run(Business business);
}
