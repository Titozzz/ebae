package ebaeapp.com.ebae.controller;

import android.location.Location;

import ebaeapp.com.ebae.model.SaveLocationModel;

/**
 * Created by Hung on 3/11/2017.
 */

public class SaveLocationController {

    public static void updateCoordinates(Location location) {
        SaveLocationModel.updateCoordinates(location);

    }
}
