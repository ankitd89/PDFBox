package model;

import java.io.IOException;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.util.DumpWriter;
import com.dropbox.core.util.Dumpable;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends Dumpable {

		  private long uid;
	      @JsonProperty("display_name")
	      private String displayName;
	 
	      private String email;
	  
	      @JsonProperty("referral_link")
	      private String referralLink;
	  
	      private String country;
	  
	    
	      public Account(long userId, String displayName, String country, String referralLink, String email)
	      {
	          this.uid = userId;
	          this.displayName = displayName;
	          this.country = country;
	          this.referralLink = referralLink;
	          this.email = email;
	      }
	      
	      @Override
	      protected void dumpFields(DumpWriter out)
	      {
	          out.field("userId", uid);
	          out.field("displayName", displayName);
	          out.field("country", country);
	          out.field("referralLink", referralLink);
	          out.field("email", email);
	      }
	      
	      
	      public static final JsonReader<Account> Reader = new JsonReader<Account>()
	    		    {
	    		        public final Account read(JsonParser parser)
	    		            throws IOException, JsonReadException
	    		        {
	    		            JsonLocation top = JsonReader.expectObjectStart(parser);

	    		            long uid = -1;
	    		            String display_name = null;
	    		            String country = null;
	    		            String referral_link = null;
	    		            String email = null;

	    		            while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
	    		                String fieldName = parser.getCurrentName();
	    		                parser.nextToken();

	    		                try {
	    		                    int fi = FM.get(fieldName);
	    		                    switch (fi) {
	    		                        case -1: JsonReader.skipValue(parser); break;
	    		                        case FM_uid: uid = JsonReader.readUnsignedLongField(parser, fieldName, uid); break;
	    		                        case FM_display_name: display_name = JsonReader.StringReader.readField(parser, fieldName, display_name); break;
	    		                        case FM_country: country = JsonReader.StringReader.readField(parser, fieldName, country); break;
	    		                        case FM_referral_link: referral_link = JsonReader.StringReader.readField(parser, fieldName, referral_link); break;
	    		                        case FM_email: email = JsonReader.StringReader.readField(parser, fieldName, email); break;
	    		                        default:
	    		                            throw new AssertionError("bad index: " + fi + ", field = \"" + fieldName + "\"");
	    		                    }
	    		                }
	    		                catch (JsonReadException ex) {
	    		                    throw ex.addFieldContext(fieldName);
	    		                }
	    		            }

	    		            JsonReader.expectObjectEnd(parser);

	    		            if (uid < 0) throw new JsonReadException("missing field \"uid\"", top);
	    		            if (display_name == null) throw new JsonReadException("missing field \"display_name\"", top);
	    		            if (country == null) throw new JsonReadException("missing field \"country\"", top);
	    		            if (referral_link == null) throw new JsonReadException("missing field \"referral_link\"", top);
	    		            if (email == null) throw new JsonReadException("missing field \"email\"", top);

	    		            return new Account(uid, display_name, country, referral_link, email);
	    		        }
	    		    };

	    		    private static final int FM_uid = 0;
	    		    private static final int FM_display_name = 1;
	    		    private static final int FM_country = 2;
	    		    private static final int FM_referral_link = 3;
	    		    private static final int FM_email = 4;
	    		    private static final JsonReader.FieldMapping FM;

	    		    static {
	    		        JsonReader.FieldMapping.Builder b = new JsonReader.FieldMapping.Builder();
	    		        b.add("uid", FM_uid);
	    		        b.add("display_name", FM_display_name);
	    		        b.add("country", FM_country);
	    		        b.add("referral_link", FM_referral_link);
	    		        b.add("email", FM_email);
	    		        FM = b.build();
	    		    }


	      public long getUid() {
	          return uid;
	      }
	 
	      public void setUid(long uid) {
	          this.uid = uid;
	      }
	 
	      public String getDisplayName() {
	          return displayName;
	      }
	  
	      public void setDisplayName(String displayName) {
	          this.displayName = displayName;
	      }
	  
	      public String getEmail() {
	          return email;
	      }
	  
	      public void setEmail(String email) {
	          this.email = email;
	      }
	  
	     public String getReferralLink() {
	      return referralLink;
	      }
	  
	      public void setReferralLink(String referralLink) {
	          this.referralLink = referralLink;
	      }
	  
	     public String getCountry() {
	          return country;
	      }
	  
	      public void setCountry(String country) {
	          this.country = country;
	      }
	
	  
}

