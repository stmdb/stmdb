# STMDB

STMDB is an experiment in combining software transactional memory
and the durability of an immutable data store.

Software transactional memory is a concurrency control paradigm
that provides atomic and isolated execution for regions of code.

STMDB uses multiversion concurrency control to track the history of
data objects. In a typical transactional memory system,
previous versions of data objects are garbage collected when they are
no longer referenced. STMDB stores this entire history in durable storage
(or it will. Currently the entire history is stored in memory). The STMDB
API allows the querying of data objects at any point or interval in time.

#### TODO List

- [ ] basic functionality. non-nested transactions
- [ ] durability
- [ ] mapping of transaction identifier to timestamp. Learn about
CLOCK_MONOTONIC and CLOCK_MONOTONIC_RAW.
- [ ] nested transactions
- [ ] replace global write lock with fine-grained locking or lock-free algorithm
- [ ] eliminate memory leak from the design. Garbage collect old versions
that have been serialized to disk.

#### Reading List

* Herlihy, Maurice, and J. Eliot B. Moss. "Transactional memory: Architectural
support for lock-free data structures." Vol. 21, no. 2. ACM SIGARCH Computer
Architecture News - Special Issue: Proceedings of the 20th annual international
symposium on Computer architecture, 1993.
* Shavit, Nir, and Dan Touitou. "Software transactional memory." Distributed
Computing 10, no. 2 (1997): 99-116.
* Moss, J. Eliot B., and Antony L. Hosking. "Nested transactional memory:
model and architecture sketches." Science of Computer Programming 63, no. 2
(2006): 186-201.
* Cachopo, João, and António Rito-Silva. "Versioned boxes as the basis for
memory transactions." Science of Computer Programming 63, no. 2 (2006): 172-185.
* Cachopo, João Manuel Pinheiro. "Development of rich domain models with atomic
actions." PhD diss., Universidade Técnica de Lisboa, 2007.
* Fernandes, Sérgio Miguel, and Joao Cachopo. "Lock-free and scalable
multi-version software transactional memory." In ACM SIGPLAN Notices, vol. 46,
no. 8, pp. 179-188. Proceedings of the 16th ACM symposium on Principles and
practice of parallel programming, 2011.
* Diegues, Nuno, and Joao Cachopo. Review of nesting in transactional memory.
Tech. rep., Technical Report RT/1/2012, Instituto Superior Técnico/INESC-ID, 2012.

#### License

STMDB is released under the Apache License Version 2.0. See
[Apache](https://www.apache.org/licenses/LICENSE-2.0) or the
LICENSE file in this distribution for details.
