package LeetcodePrograms.src.TrieAndFileSystem;

/*
"""
In our CI systems we often work with data structures dealing with Test passes or failures. You are implementing a feature to display the status
of an entire test suite, where a test suite is a tree composed of the results of many individual tests. When the page loads you will have a uuid
for this result tree (id of the root node) and need to use the REST API to call and build every node of the result tree into one object. The REST
API only returns one node at a time.

Example of a result tree:

root/
├─ parent/
│  ├─ result (pass✅)
│  ├─ result (pass✅)
├─ parent/
│  ├─ parent/
│  │  ├─ result (fail❌)
│  │  ├─ result (pass✅)
│  ├─ parent/
│  │  ├─ result (pass✅)


GET /api/result/<id>
{
    "id": "17a2b057-4042-438b-a06c-7ac6d7ab7f76",
    "parent": "07str057-4042-438b-a06c-7acstrstt5ss5", # An id or null
    "status": "pass",  # pass, fail, or null
    "children": ["8903dd9f-0726-43bd-a305-27de8e9fc854", "8065da48-06ba-4168-a50d-727eef50bc63", "cad6437c-a839-4c53-902a-9cbe843e5176"]
}


Notes:
- Status can be "pass" or "fail" or null for parents/root.
- Every node has an id and a parent.
- Parent id of the root result is null.
- Not all nodes have children.


Your goal is to return:
{
  "id": "17a2b057-4042-438b-a06c-7ac6d7ab7f76",
  "children": [
    { "id": "07str057-4042-438b-a06c-7acstrstt5ss5", "children": [
      { "id": uuid, "status", "pass✅" },
      { "id": uuid, "status", "pass✅" }
    ]},
    { "id": uuid, "children": [
      { "id": uuid, "children": [
        { "id": uuid, "status", "fail❌" },
        { "id": uuid, "status", "pass✅" }
      ]},
      { "id": uuid, "children": [
        { "id": uuid, "status", "pass✅" }
      ]}
    ]}
  ]
}
"""

        def buildResultTree(rootId):
        pass


        print("Begin")
        tree = buildResultTree("17a2b057-4042-438b-a06c-7ac6d7ab7f76")
        print(f"Tree: {tree}")
*/
public class CISystem {
}
