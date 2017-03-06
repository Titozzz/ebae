package ebaeapp.com.ebae;

import com.yelp.clientlib.entities.Business;

/**
 * Created by thiba on 05/03/2017.
 */

@FunctionalInterface
public interface BusinessRunnable {
  void run(Business business);
}
