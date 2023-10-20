package LeetcodePrograms.InterviewQuestions.LinkedinCompactTreeBuilder;

// https://leetcode.com/discuss/interview-question/124893/LinkedIn-or-Phone-Screen-or-Compact-Tree-Builder
    public interface CompactTreeBuilder<T> {
        /**
         * Given a root of a tree. The tree may be of any depth and width,
         * i.e. each node may have any number of child nodes.
         * This method should transform a tree in such a way
         * that each node (except probably one) would either have N or 0 children
         * (and one node may have a number of children between 0 and N).
         *
         * Algorithm may transform given tree in any way with only condition:
         * if node A was an ascendant of node B in a source tree
         * node B may not be an ascendant of a node A in a result tree (they may become siblings though).
         *
         * E.g.
         *
         * source:        compact(A, 2)     compact(A, 1)             compact(A, 100)
         *
         * A               A                 A                         A
         *  |               |                 |_B                       |_B
         *  |_B             |_B                  |_C                    |
         *     |            |  |                    |_D                 |_C
         *     |            |  |_D                     |_E              |
         *     |            |  |                          |_F           |_D
         *     |_C          |  |_E                           |_G        |
         *     | |_D        |    |_H                            |_H     |_E
         *     |    |_F     |                                           |
         *     |            |_C                                         |_F
         *     |_E            |                                         |
         *       |_G          |_F                                       |_G
         *       |            |                                         |
         *       |_H          |_G                                       |_H
         *
         *
         *  in an example for compact(A,2) above node E is an exception node:
         *  it has 1 child while any other node has either 2 or 0 children
         */
        Node<T> compact(Node root, int N);
    }

