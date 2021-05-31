// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WSMessageResProto.proto

package com.dikar.common.protobuf;

public final class WSMessageResProtoOuterClass {
    private WSMessageResProtoOuterClass() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public interface WSMessageResProtoOrBuilder extends
            // @@protoc_insertion_point(interface_extends:protocol.WSMessageResProto)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>uint64 receive_id = 1;</code>
         */
        long getReceiveId();

        /**
         * <code>int32 msg_type = 2;</code>
         */
        int getMsgType();

        /**
         * <code>string msg_content = 3;</code>
         */
        java.lang.String getMsgContent();

        /**
         * <code>string msg_content = 3;</code>
         */
        com.google.protobuf.ByteString
        getMsgContentBytes();
    }

    /**
     * <pre>
     * 请求实体
     * </pre>
     * <p>
     * Protobuf type {@code protocol.WSMessageResProto}
     */
    public static final class WSMessageResProto extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:protocol.WSMessageResProto)
            WSMessageResProtoOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use WSMessageResProto.newBuilder() to construct.
        private WSMessageResProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private WSMessageResProto() {
            msgContent_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private WSMessageResProto(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            receiveId_ = input.readUInt64();
                            break;
                        }
                        case 16: {

                            msgType_ = input.readInt32();
                            break;
                        }
                        case 26: {
                            java.lang.String s = input.readStringRequireUtf8();

                            msgContent_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.dikar.common.protobuf.WSMessageResProtoOuterClass.internal_static_protocol_WSMessageResProto_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.dikar.common.protobuf.WSMessageResProtoOuterClass.internal_static_protocol_WSMessageResProto_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.class, com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.Builder.class);
        }

        public static final int RECEIVE_ID_FIELD_NUMBER = 1;
        private long receiveId_;

        /**
         * <code>uint64 receive_id = 1;</code>
         */
        public long getReceiveId() {
            return receiveId_;
        }

        public static final int MSG_TYPE_FIELD_NUMBER = 2;
        private int msgType_;

        /**
         * <code>int32 msg_type = 2;</code>
         */
        public int getMsgType() {
            return msgType_;
        }

        public static final int MSG_CONTENT_FIELD_NUMBER = 3;
        private volatile java.lang.Object msgContent_;

        /**
         * <code>string msg_content = 3;</code>
         */
        public java.lang.String getMsgContent() {
            java.lang.Object ref = msgContent_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                msgContent_ = s;
                return s;
            }
        }

        /**
         * <code>string msg_content = 3;</code>
         */
        public com.google.protobuf.ByteString
        getMsgContentBytes() {
            java.lang.Object ref = msgContent_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                msgContent_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (receiveId_ != 0L) {
                output.writeUInt64(1, receiveId_);
            }
            if (msgType_ != 0) {
                output.writeInt32(2, msgType_);
            }
            if (!getMsgContentBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 3, msgContent_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (receiveId_ != 0L) {
                size += com.google.protobuf.CodedOutputStream
                        .computeUInt64Size(1, receiveId_);
            }
            if (msgType_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, msgType_);
            }
            if (!getMsgContentBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, msgContent_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto)) {
                return super.equals(obj);
            }
            com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto other = (com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto) obj;

            if (getReceiveId()
                    != other.getReceiveId()) return false;
            if (getMsgType()
                    != other.getMsgType()) return false;
            if (!getMsgContent()
                    .equals(other.getMsgContent())) return false;
            if (!unknownFields.equals(other.unknownFields)) return false;
            return true;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + RECEIVE_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                    getReceiveId());
            hash = (37 * hash) + MSG_TYPE_FIELD_NUMBER;
            hash = (53 * hash) + getMsgType();
            hash = (37 * hash) + MSG_CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getMsgContent().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * <pre>
         * 请求实体
         * </pre>
         * <p>
         * Protobuf type {@code protocol.WSMessageResProto}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:protocol.WSMessageResProto)
                com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProtoOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.dikar.common.protobuf.WSMessageResProtoOuterClass.internal_static_protocol_WSMessageResProto_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.dikar.common.protobuf.WSMessageResProtoOuterClass.internal_static_protocol_WSMessageResProto_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.class, com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.Builder.class);
            }

            // Construct using com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                receiveId_ = 0L;

                msgType_ = 0;

                msgContent_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.dikar.common.protobuf.WSMessageResProtoOuterClass.internal_static_protocol_WSMessageResProto_descriptor;
            }

            @java.lang.Override
            public com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto getDefaultInstanceForType() {
                return com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.getDefaultInstance();
            }

            @java.lang.Override
            public com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto build() {
                com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto buildPartial() {
                com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto result = new com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto(this);
                result.receiveId_ = receiveId_;
                result.msgType_ = msgType_;
                result.msgContent_ = msgContent_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto) {
                    return mergeFrom((com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto other) {
                if (other == com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto.getDefaultInstance())
                    return this;
                if (other.getReceiveId() != 0L) {
                    setReceiveId(other.getReceiveId());
                }
                if (other.getMsgType() != 0) {
                    setMsgType(other.getMsgType());
                }
                if (!other.getMsgContent().isEmpty()) {
                    msgContent_ = other.msgContent_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private long receiveId_;

            /**
             * <code>uint64 receive_id = 1;</code>
             */
            public long getReceiveId() {
                return receiveId_;
            }

            /**
             * <code>uint64 receive_id = 1;</code>
             */
            public Builder setReceiveId(long value) {

                receiveId_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>uint64 receive_id = 1;</code>
             */
            public Builder clearReceiveId() {

                receiveId_ = 0L;
                onChanged();
                return this;
            }

            private int msgType_;

            /**
             * <code>int32 msg_type = 2;</code>
             */
            public int getMsgType() {
                return msgType_;
            }

            /**
             * <code>int32 msg_type = 2;</code>
             */
            public Builder setMsgType(int value) {

                msgType_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 msg_type = 2;</code>
             */
            public Builder clearMsgType() {

                msgType_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object msgContent_ = "";

            /**
             * <code>string msg_content = 3;</code>
             */
            public java.lang.String getMsgContent() {
                java.lang.Object ref = msgContent_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    msgContent_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string msg_content = 3;</code>
             */
            public com.google.protobuf.ByteString
            getMsgContentBytes() {
                java.lang.Object ref = msgContent_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    msgContent_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string msg_content = 3;</code>
             */
            public Builder setMsgContent(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                msgContent_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string msg_content = 3;</code>
             */
            public Builder clearMsgContent() {

                msgContent_ = getDefaultInstance().getMsgContent();
                onChanged();
                return this;
            }

            /**
             * <code>string msg_content = 3;</code>
             */
            public Builder setMsgContentBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                msgContent_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:protocol.WSMessageResProto)
        }

        // @@protoc_insertion_point(class_scope:protocol.WSMessageResProto)
        private static final com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto();
        }

        public static com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<WSMessageResProto>
                PARSER = new com.google.protobuf.AbstractParser<WSMessageResProto>() {
            @java.lang.Override
            public WSMessageResProto parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new WSMessageResProto(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<WSMessageResProto> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<WSMessageResProto> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public com.dikar.common.protobuf.WSMessageResProtoOuterClass.WSMessageResProto getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_protocol_WSMessageResProto_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_protocol_WSMessageResProto_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\027WSMessageResProto.proto\022\010protocol\"N\n\021W" +
                        "SMessageResProto\022\022\n\nreceive_id\030\001 \001(\004\022\020\n\010" +
                        "msg_type\030\002 \001(\005\022\023\n\013msg_content\030\003 \001(\tB#\n\037c" +
                        "om.dikar.common.protobufH\001b\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_protocol_WSMessageResProto_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_protocol_WSMessageResProto_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_protocol_WSMessageResProto_descriptor,
                new java.lang.String[]{"ReceiveId", "MsgType", "MsgContent",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}