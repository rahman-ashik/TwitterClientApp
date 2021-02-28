
package com.codepath.apps.restclienttemplate.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Tweet$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.restclienttemplate.models.Tweet>
{

    private com.codepath.apps.restclienttemplate.models.Tweet tweet$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Tweet$$Parcelable>CREATOR = new Creator<Tweet$$Parcelable>() {


        @Override
        public Tweet$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Tweet$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Tweet$$Parcelable[] newArray(int size) {
            return new Tweet$$Parcelable[size] ;
        }

    }
    ;

    public Tweet$$Parcelable(com.codepath.apps.restclienttemplate.models.Tweet tweet$$2) {
        tweet$$0 = tweet$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(tweet$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.codepath.apps.restclienttemplate.models.Tweet tweet$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(tweet$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(tweet$$1));
            parcel$$1 .writeString(tweet$$1 .createdAt);
            parcel$$1 .writeString(tweet$$1 .body);
            com.codepath.apps.restclienttemplate.models.User$$Parcelable.write(tweet$$1 .user, parcel$$1, flags$$0, identityMap$$0);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.restclienttemplate.models.Tweet getParcel() {
        return tweet$$0;
    }

    public static com.codepath.apps.restclienttemplate.models.Tweet read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.codepath.apps.restclienttemplate.models.Tweet tweet$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            tweet$$4 = new com.codepath.apps.restclienttemplate.models.Tweet();
            identityMap$$1 .put(reservation$$0, tweet$$4);
            tweet$$4 .createdAt = parcel$$3 .readString();
            tweet$$4 .body = parcel$$3 .readString();
            User user$$0 = com.codepath.apps.restclienttemplate.models.User$$Parcelable.read(parcel$$3, identityMap$$1);
            tweet$$4 .user = user$$0;
            com.codepath.apps.restclienttemplate.models.Tweet tweet$$3 = tweet$$4;
            identityMap$$1 .put(identity$$1, tweet$$3);
            return tweet$$3;
        }
    }

}
