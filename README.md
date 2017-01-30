# SyncMe
[![SyncMe Logo](https://syncme.files.wordpress.com/2016/04/header.png?w=624)](https://syncme.wordpress.com/)<br><br>
Build Status: 

SyncMe is a cross-platform application (Linux, Windows, Mac) with the main goal of unify the synchronization of Dropbox and Google Drive platforms into a single application, allowing you to manage all your files easily.

Developer website: [Adrián Rodríguez Bazaga](http://www.adrianbazaga.com/)

Please note that development is very early on and the project is not ready to use yet.

## Version

Currently SyncMe has been ported to Java and updated to SyncMe v2 in order to work with the newest versions of Dropbox API (2.0) and Google Drive API (3.0), original SyncMe (v1) was intended to be a prototype using Google Drive API 2.0 and Dropbox API 1.0 HTTP calls, but now API calls from that version are no longer supported neither by Google or Dropbox.

## Dependencies

| Name         | Version                          |
|--------------|----------------------------------|
| Java           | >= 8                        |
| Gradle | >= 3.x |
| Groovy      |              Any                    |
| Kotlin      |              >= 1.x                    |
| JUnit      |              >= 4.x                    |
| Dropbox API SDK      |              >= 2.1.x                    |
| Google API SDK      |              >= 1.2x.y                    |

## Building

See [INSTALL.md](INSTALL.md)

## Usage

See [USAGE.md](USAGE.md)

## Contributing

1. Find a thing to fix/implement in [Issues](https://github.com/AdrianBZG/SyncMe/issues?direction=desc&sort=created&state=open) or come up with your own idea, [create a discussion issue](https://github.com/AdrianBZG/SyncMe/issues/new) for it and get a feedback.
2. [Fork the repo](https://help.github.com/articles/fork-a-repo)
3. Create your feature branch (`git checkout -b my-new-feature`)
4. Commit your changes (`git commit -am 'Add some feature'`)
5. Push to the branch (`git push origin my-new-feature`)
6. [Create new Pull Request](https://help.github.com/articles/using-pull-requests)

## Contact

You can contact me by sending an email to [arodriba@ull.es](mailto:arodriba@ull.es)

LICENSE
=======
``` java
Copyright 2017 Adrián Rodríguez Bazaga

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

## Additional license terms
``` java
1. a
2. b
3. c
```