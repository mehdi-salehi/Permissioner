[![](https://jitpack.io/v/mehdi-salehi/Permissioner.svg)](https://jitpack.io/#mehdi-salehi/Permissioner)

#Permissioner

request android permissions at runtime in simple way...
         
           
1. Request Permissions.
2. If user denied permissions, we will show message dialog with Setting button.


##Setup


###Gradle

```javascript

allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

```
and
```gradle
	dependencies {
	        compile 'com.github.mehdi-salehi:Permissioner:1.0.0'
	}
```


##How to use


###1. Make PermissionListener
We will use PermissionListener for Permission Result.
You will get result to `onPermissionGranted()`, `onPermissionDenied()`

```javascript

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }


    };


```

<br/>
###2. Start Permissioner


```javascript

    new Permissioner(this)
    .setPermissionListener(permissionlistener)
    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
    .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
    .check();

```

