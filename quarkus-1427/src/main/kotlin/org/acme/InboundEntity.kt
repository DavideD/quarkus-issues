package org.acme

import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Cacheable
@Entity(name = "inbd_trans")
data class InboundEntity(
    @Id
    @Column(name = "trans_msg_id", length = 40, nullable = false)
    var transactionMessageId: String? = "",

    @Column(name = "trans_msg_tx", length = 20000, nullable = false)
    var inboundTransaction: String? = "",

    @Column(name = "creat_user_id", length = 34, nullable = false)
    var createdUserId: String? = "",
)