package com.higherfrequencytrading.chronicle.impl;

import com.higherfrequencytrading.chronicle.tools.ChronicleTools;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteOrder;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-30
 */
public class ChronicleBuilder {

    @NotNull
    public static IndexedChronicleBuilder newIndexedChronicleBuilder(String basePath) {
        return new IndexedChronicleBuilder(basePath);
    }

    @NotNull
    public static IntIndexedChronicleBuilder newIntIndexedChronicleBuilder(String basePath) {
        return new IntIndexedChronicleBuilder(basePath);
    }

    public static class IndexedChronicleBuilder {

        protected String basePath;
        protected int dataBitSizeHint =
                ChronicleTools.is64Bit() ? IndexedChronicle.DEFAULT_DATA_BITS_SIZE : IndexedChronicle.DEFAULT_DATA_BITS_SIZE32;
        protected ByteOrder byteOrder = ByteOrder.nativeOrder();
        protected boolean minimiseByteBuffers = !ChronicleTools.is64Bit();
        protected boolean synchronousMode = false;
        protected boolean useUnsafe = false;

        public IndexedChronicleBuilder(String basePath) {
            this.basePath = basePath;
        }

        @NotNull
        public IndexedChronicleBuilder dataBitSizeHint(int dataBitSizeHint) {
            this.dataBitSizeHint = dataBitSizeHint;
            return this;
        }

        @NotNull
        public IndexedChronicleBuilder byteOrder(ByteOrder byteOrder) {
            this.byteOrder = byteOrder;
            return this;
        }

        @NotNull
        public IndexedChronicleBuilder minimiseByteBuffers(boolean minimiseByteBuffers) {
            this.minimiseByteBuffers = minimiseByteBuffers;
            return this;
        }

        @NotNull
        public IndexedChronicleBuilder useSynchronousMode(boolean synchronousMode) {
            this.synchronousMode = synchronousMode;
            return this;
        }

        @NotNull
        public IndexedChronicleBuilder useUnsafe(boolean useUnsafe) {
            this.useUnsafe = useUnsafe;
            return this;
        }

        @NotNull
        public IndexedChronicle build() throws IOException {
            IndexedChronicle indexedChronicle =
                    new IndexedChronicle(basePath, dataBitSizeHint, byteOrder, minimiseByteBuffers, synchronousMode);
            indexedChronicle.useUnsafe(useUnsafe);
            return indexedChronicle;
        }
    }

    public static class IntIndexedChronicleBuilder extends IndexedChronicleBuilder {

        public IntIndexedChronicleBuilder(String basePath) {
            super(basePath);
        }

        @NotNull
        @Override
        public IntIndexedChronicle build() throws IOException {
            IntIndexedChronicle intIndexedChronicle = new IntIndexedChronicle(basePath, dataBitSizeHint, byteOrder);
            intIndexedChronicle.useUnsafe(useUnsafe);
            return intIndexedChronicle;
        }
    }


}